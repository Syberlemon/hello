package wh.mapper;

import org.apache.ibatis.annotations.Mapper;
import wh.bean.DecisionReq;
import wh.bean.DecisionRes;

import java.util.List;
import java.util.Map;

/**
 * @author froid
 * @date 2020/8/25 21:33
 */
@Mapper
public interface DecisionMapper {
    List<DecisionRes> getAllDecisions(DecisionReq req);

    List<String> getDecisionDetail(String id);

    int addDecision(Map<String, Object> params);

    int addDecisionDetail(Map<String, Object> params);

    int delDecisionDetail(Map<String, Object> params);
}
