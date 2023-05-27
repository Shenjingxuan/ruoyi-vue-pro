package cn.iocoder.yudao.module.trade.controller.admin.delivery;

import cn.iocoder.yudao.module.trade.controller.admin.delivery.vo.*;
import cn.iocoder.yudao.module.trade.convert.delivery.DeliveryExpressTemplateConvert;
import cn.iocoder.yudao.module.trade.dal.dataobject.delivery.DeliveryExpressTemplateDO;
import cn.iocoder.yudao.module.trade.service.delivery.DeliveryExpressTemplateService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 快递运费模板")
@RestController
@RequestMapping("/trade/delivery/express-template")
@Validated
public class DeliveryExpressTemplateController {

    @Resource
    private DeliveryExpressTemplateService deliveryExpressTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建快递运费模板")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express-template:create')")
    public CommonResult<Long> createDeliveryExpressTemplate(@Valid @RequestBody DeliveryExpressTemplateCreateReqVO createReqVO) {
        return success(deliveryExpressTemplateService.createDeliveryExpressTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新快递运费模板")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express-template:update')")
    public CommonResult<Boolean> updateDeliveryExpressTemplate(@Valid @RequestBody DeliveryExpressTemplateUpdateReqVO updateReqVO) {
        deliveryExpressTemplateService.updateDeliveryExpressTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除快递运费模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('trade:delivery:express-template:delete')")
    public CommonResult<Boolean> deleteDeliveryExpressTemplate(@RequestParam("id") Long id) {
        deliveryExpressTemplateService.deleteDeliveryExpressTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得快递运费模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express-template:query')")
    public CommonResult<DeliveryExpressTemplateRespVO> getDeliveryExpressTemplate(@RequestParam("id") Long id) {
        return success(deliveryExpressTemplateService.getDeliveryExpressTemplate(id));
    }

    @GetMapping("/list")
    @Operation(summary = "获得快递运费模板列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express-template:query')")
    public CommonResult<List<DeliveryExpressTemplateSimpleRespVO>> getDeliveryExpressTemplateList(@RequestParam("ids") Collection<Long> ids) {
        List<DeliveryExpressTemplateDO> list = deliveryExpressTemplateService.getDeliveryExpressTemplateList(ids);
        return success(DeliveryExpressTemplateConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得快递运费模板分页")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express-template:query')")
    public CommonResult<PageResult<DeliveryExpressTemplateSimpleRespVO>> getDeliveryExpressTemplatePage(@Valid DeliveryExpressTemplatePageReqVO pageVO) {
        PageResult<DeliveryExpressTemplateDO> pageResult = deliveryExpressTemplateService.getDeliveryExpressTemplatePage(pageVO);
        return success(DeliveryExpressTemplateConvert.INSTANCE.convertPage(pageResult));
    }
}
