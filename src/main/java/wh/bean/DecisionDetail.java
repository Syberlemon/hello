package wh.bean;

import lombok.Data;

import java.util.List;

/**
 * @author froid
 * Date:  2021/5/23
 */
@Data
public class DecisionDetail {
    private String decisionID;
    private String decisionName;
    private List<String> detailNameList;
}
