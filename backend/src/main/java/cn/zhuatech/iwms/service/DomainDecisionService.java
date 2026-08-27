/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.iwms.service;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class DomainDecisionService {
 public DecisionResult assess(DecisionRequest request) { double consumed=request.elapsedHours()*100d/request.slaHours();int score=100;List<String> actions=new ArrayList<>();if(!request.technicianAssigned()){score-=30;actions.add("立即分派合格技师");}if(!request.partsAvailable()){score-=20;actions.add("协调关键备件");}if(request.safetyPermitRequired()&&!request.safetyPermitApproved()){score-=50;actions.add("取得作业许可前禁止开工");}if(consumed>=100){score-=30;actions.add("启动SLA违约升级");}else if(consumed>=80){score-=15;actions.add("提升工单优先级");}return result(score,actions,"READY","AT_RISK","BLOCKED",Map.of("slaConsumedPercent",Math.round(consumed),"remainingHours",Math.max(0,request.slaHours()-request.elapsedHours()),"permitApproved",request.safetyPermitApproved())); }
 private DecisionResult result(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=80?good:score>=50?warn:bad;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 private DecisionResult riskResult(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=70?bad:score>=40?warn:good;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 public record DecisionRequest(
        @NotBlank String workOrderNo,
        @NotBlank String priority,
        @PositiveOrZero int elapsedHours,
        @Positive int slaHours,
        boolean technicianAssigned,
        boolean partsAvailable,
        boolean safetyPermitRequired,
        boolean safetyPermitApproved) {}
 public record DecisionResult(String decision,int score,Map<String,Object> metrics,List<String> actions) {}
}
