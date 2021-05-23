package wh.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wh.bean.DecisionDetail;
import wh.bean.DecisionReq;
import wh.bean.DecisionRes;
import wh.mapper.DecisionMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author froid
 * @date 2020/8/25 21:33
 * 解决经常忘记密码的问题
 */
@RestController
@Api(value = "小决定大转盘")
public class DecisionController {
    @Autowired
    private DecisionMapper decisionMapper;

    @RequestMapping("/getAllDecisions")
    @ApiOperation(value = "获取小决定列表")
    public List<DecisionRes> getAllDecisions(DecisionReq req) {
        return decisionMapper.getAllDecisions(req);
    }

    @RequestMapping("/getDecisionDetail/{id}")
    @ApiOperation(value = "获取某个小决定详情")
    public List<String> getDecisionDetail(@PathVariable String id) {
        return decisionMapper.getDecisionDetail(id);
    }

    @RequestMapping("/addDecision")
    @ApiOperation(value = "新增小决定")
    public int addDecision(DecisionDetail req) {
        Map<String, Object> decisionReq = new HashMap<>();
        decisionReq.put("name", req.getDecisionName());
        int decisionID = decisionMapper.addDecision(decisionReq);
        Map<String, Object> detailReq = new HashMap<>();
        detailReq.put("decisionID", decisionID);
        detailReq.put("nameList", req.getDetailNameList());
        decisionMapper.addDecisionDetail(detailReq);
        return decisionID;
    }

    @RequestMapping("/updateDecision")
    @ApiOperation(value = "修改小决定")
    public int updateDecision(DecisionDetail req) {
        List<String> nowNameList = decisionMapper.getDecisionDetail(req.getDecisionID());
        List<String> addNameList = new ArrayList<>();
        List<String> updateNameList = new ArrayList<>();
        for(String name: req.getDetailNameList()){
            if(!nowNameList.contains(name)){
                addNameList.add(name);
            } else {
                updateNameList.add(name);
            }
        }
        List<String> delNameList = req.getDetailNameList().stream()
                .filter(m->!addNameList.contains(m))
                .filter(m->!updateNameList.contains(m))
                .collect(Collectors.toList());
        Map<String, Object> detailReq = new HashMap<>();
        detailReq.put("decisionID", req.getDecisionID());
        if(addNameList != null && addNameList.size() > 0){
            detailReq.put("nameList", addNameList);
            decisionMapper.addDecisionDetail(detailReq);
        }
        if(delNameList != null && delNameList.size() > 0){
            detailReq.put("nameList", delNameList);
            decisionMapper.delDecisionDetail(detailReq);
        }
        return 1;
    }

}
