/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.iwms.deep;import cn.zhuatech.iwms.common.ApiResponse;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/admin/facility-ops") public class DeepAdminController{private final DeepDomainService s;/**
                                                                                                                               * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                               */
public DeepAdminController(DeepDomainService s){this.s=s;}/**
                                                                                                                                                                                         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                         */
@PostMapping("/assets/{id}/activate")ApiResponse<?> activate(@PathVariable Long id){return ApiResponse.ok(s.activate(id));}/**
                                                                                                                                                                                                                                                                                                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                    */
@PostMapping("/work-orders/{id}/accept")ApiResponse<?> accept(@PathVariable Long id,@Valid @RequestBody DeepDomainService.AcceptRequest r){return ApiResponse.ok(s.accept(id,r));}}
