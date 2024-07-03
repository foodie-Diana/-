package com.ruoyi.web.controller.mes;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mes/project")
public class MesProjectController extends BaseController {

    @GetMapping
    public AjaxResult list(){
        //return AjaxResult.success("success", "项目维护列表");
        return super.success("项目维护列表");

    }
}
