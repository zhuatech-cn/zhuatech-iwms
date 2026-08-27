/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.iwms.domain;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();
    public DomainCatalog() {
        actions.put("DISPATCH", new WorkflowAction("DISPATCH", "派发任务", List.of("草稿"), "执行中", "OPERATOR"));
        actions.put("VERIFY", new WorkflowAction("VERIFY", "验收服务", List.of("执行中"), "待结算", "ADMIN"));
        actions.put("SETTLE", new WorkflowAction("SETTLE", "确认结算", List.of("待结算"), "已完成", "ADMIN"));
    }
    public String systemName() { return "知华科技企业不动产与设施管理系统"; }
    public String scene() { return "不动产、租赁、空间、工位、设施、维护、服务、能耗、项目与供应商"; }
    public String initialStatus() { return "草稿"; }
    public String partyLabel() { return "园区/设施"; }
    public String amountLabel() { return "运营成本"; }
    public String quantityLabel() { return "空间或设备数"; }
    public String dueLabel() { return "服务期限"; }
    public List<ModuleDefinition> modules() { return List.of(
            new ModuleDefinition("PROPERTY", "不动产台账", "管理园区、楼宇、权属和估值信息"),
            new ModuleDefinition("LEASE", "租赁管理", "管理合同、租金、账期、续租和退租"),
            new ModuleDefinition("SPACE", "空间管理", "维护楼层、区域、面积、用途和容量"),
            new ModuleDefinition("WORKPLACE", "工位服务", "支持工位、会议室和访客空间预订"),
            new ModuleDefinition("FACILITY", "设施设备", "维护设备、位置、保修和关键等级"),
            new ModuleDefinition("MAINTENANCE", "维护保养", "生成计划、工单、点检和故障闭环"),
            new ModuleDefinition("SERVICE_REQUEST", "服务请求", "受理保洁、安保、搬迁和行政服务"),
            new ModuleDefinition("ENERGY", "能源管理", "采集水电气、分析强度并跟踪节能"),
            new ModuleDefinition("PROJECT", "改造项目", "管理装修、搬迁和资本性改造"),
            new ModuleDefinition("VENDOR", "服务商管理", "管理合同、人员、绩效和安全准入")
        ); }
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }
    public record ModuleDefinition(String code,String name,String description) {}
    public record WorkflowAction(String code,String label,List<String> from,String to,String requiredRole) {}
}
