package com.ruoyi.web.controller.mes;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.employment.domain.MesProduct;
import com.ruoyi.employment.service.IMesProductService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品建模Controller
 * 
 * @author ruoyi
 * @date 2024-07-01
 */
@RestController
@RequestMapping("/mes/product")
public class MesProductController extends BaseController
{
    @Autowired
    private IMesProductService mesProductService;

    /**
     * 查询产品建模列表
     */
    @PreAuthorize("@ss.hasPermi('mes:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesProduct mesProduct)
    {
        startPage();
        List<MesProduct> list = mesProductService.selectMesProductList(mesProduct);
        return getDataTable(list);
    }

    /**
     * 导出产品建模列表
     */
    @PreAuthorize("@ss.hasPermi('mes:product:export')")
    @Log(title = "产品建模", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesProduct mesProduct)
    {
        List<MesProduct> list = mesProductService.selectMesProductList(mesProduct);
        ExcelUtil<MesProduct> util = new ExcelUtil<MesProduct>(MesProduct.class);
        util.exportExcel(response, list, "产品建模数据");
    }

    /**
     * 获取产品建模详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mesProductService.selectMesProductById(id));
    }

    /**
     * 新增产品建模
     */
    @PreAuthorize("@ss.hasPermi('mes:product:add')")
    @Log(title = "产品建模", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesProduct mesProduct)
    {
        return toAjax(mesProductService.insertMesProduct(mesProduct));
    }

    /**
     * 修改产品建模
     */
    @PreAuthorize("@ss.hasPermi('mes:product:edit')")
    @Log(title = "产品建模", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesProduct mesProduct)
    {
        return toAjax(mesProductService.updateMesProduct(mesProduct));
    }

    /**
     * 删除产品建模
     */
    @PreAuthorize("@ss.hasPermi('mes:product:remove')")
    @Log(title = "产品建模", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mesProductService.deleteMesProductByIds(ids));
    }
}
