package com.candeus.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.candeus.product.common.result.Result;
import com.candeus.product.domain.excel.ProductExcelData;
import com.candeus.product.domain.pojo.CustomTextConfig;
import com.candeus.product.domain.pojo.FieldDisplayConfig;
import com.candeus.product.domain.pojo.Product;
import com.candeus.product.domain.req.ProductForm;
import com.candeus.product.domain.vo.AdminVo;
import com.candeus.product.mapper.ProductMapper;
import com.candeus.product.service.CustomTextConfigService;
import com.candeus.product.service.FieldDisplayConfigService;
import com.candeus.product.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.candeus.product.tool.AdminHolder;
import com.candeus.product.tool.SpringBeanUtil;
import com.candeus.product.tool.TimestampUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.candeus.product.common.constant.AdminLevelConstant.SUPER_ADMIN;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Codenj
 * @since 2024-07-12
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private FieldDisplayConfigService fieldDisplayConfigService;

    @Resource
    private CustomTextConfigService customTextConfigService;


    @Override
    @Transactional
    public Result add(ProductForm productForm) {
        //查询产品序列号产品是否已经存在
        String productSerial = productForm.getProductSerial();
        Product productInDB = productMapper.selectByProductSerial(productSerial);
        if (productInDB != null){
            return Result.fail("该产品序列号内容已经存在！");
        }else{
            Product product = SpringBeanUtil.copyProperties(productForm, Product.class);
            product.setCreatedAt(TimestampUtil.getCurrentTimestamp().toString());
            product.setUpdatedAt(TimestampUtil.getCurrentTimestamp().toString());
            productMapper.insert(product);
            return Result.ok();
        }
    }

    @Override
    @Transactional
    public Result importProducts(MultipartFile file) {
        if(file.isEmpty()){
            return Result.fail("文件不能为空！");
        }
        try {
            List<ProductExcelData> dataList = new ArrayList<>();
            // 读取Excel文件并将数据添加到dataList中
            EasyExcel.read(file.getInputStream(), ProductExcelData.class, new PageReadListener<ProductExcelData>(dataList::addAll)).sheet().doRead();
            // 检查dataList中的产品序列号空值情况
            List<Integer> errorRowIds = checkProductExcelDataList(dataList);
            if (!errorRowIds.isEmpty()){
                return Result.fail("第"+errorRowIds+"行的产品序列号存在空值，请仔细检查");
            }
            List<Product> productList = dataList.stream()
                    .map(productExcelData -> {
                        Product product = new Product();
                        BeanUtil.copyProperties(productExcelData, product);
                        product.setCreatedAt(TimestampUtil.getCurrentTimestamp().toString());
                        product.setUpdatedAt(TimestampUtil.getCurrentTimestamp().toString());
                        return product;
                    })
                    .collect(Collectors.toList());
            saveOrUpdateBatch(productList);
            return Result.ok();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.fail("导入失败：" + e.getMessage());
        }
    }

    private List<Integer> checkProductExcelDataList(List<ProductExcelData> dataList) {
        return dataList.stream()
                .filter(data -> data.getProductSerial() == null || data.getProductSerial().isEmpty())
                .map(data -> dataList.indexOf(data) + 1)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Result updateProduct(String id, ProductForm productForm) {
        AdminVo admin = AdminHolder.getAdmin();
        Integer adminLevel = admin.getAdminLevel();
        if (adminLevel != SUPER_ADMIN.getLevel()) {
            return Result.build(40010,"权限不足,请联系超级管理员!",null);
        }
        Product productInDB = productMapper.selectById(id);
        if (productInDB != null){
            productInDB.setBrand(productForm.getBrand());
            productInDB.setProductType(productForm.getProductType());
            productInDB.setProductModel(productForm.getProductModel());
            productInDB.setProductSerial(productForm.getProductSerial());
            productInDB.setProductionCycle(productForm.getProductionCycle());
            productInDB.setProxyFactory(productForm.getProxyFactory());
            productInDB.setUpdatedAt(TimestampUtil.getCurrentTimestamp().toString());
            productMapper.updateById(productInDB);
            return Result.ok();
        }else{
            return Result.fail("该产品不存在！");
        }
    }

    @Override
    @Transactional
    public Result deleteProduct(String id) {
        AdminVo admin = AdminHolder.getAdmin();
        Integer adminLevel = admin.getAdminLevel();
        if (adminLevel != SUPER_ADMIN.getLevel()) {
            return Result.build(40010,"权限不足,请联系超级管理员!",null);
        }
        Product productInDB = productMapper.selectById(id);
        if (productInDB != null){
            productInDB.setIsDeleted(true);
            productMapper.updateById(productInDB);
            return Result.ok();
        }else{
            return Result.fail("该产品不存在！");
        }
    }

    @Override
    public Result getProduct(String id) {
        Product product = productMapper.selectById(id);
        if (product != null){
            return Result.ok(product);
        }else{
            return Result.fail("该产品不存在！");
        }
    }

    @Override
    public Result getProductList(String pageNum, String pageSize, String productSerial) {
        Page<Product> page = new Page<>(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false);
        if (productSerial != null){
            queryWrapper.like("product_serial", productSerial);
        }
        queryWrapper.orderByAsc("id");
        Page<Product> productPage = productMapper.selectPage(page, queryWrapper);
        return Result.ok(productPage);
    }

    @Override
    public Result getSearchProductList(String pageNum, String pageSize, String productSerial) {
        //校验参数
        if (StringUtils.isEmpty(productSerial)) {
            return Result.fail("产品序列号不能为空！");
        }
        // 封装返回结果
        HashMap<String, Object> result = new HashMap<>();
        // 分页
        Page<Product> page = new Page<>(Integer.parseInt(pageNum), Integer.parseInt(pageSize));

        // 查询字段配置表
        List<FieldDisplayConfig> fieldDisplayConfigList = fieldDisplayConfigService.getDisplyConfigList();
        List<String> fieldNameList = fieldDisplayConfigList.stream()
                .filter(FieldDisplayConfig::getIsDisplay)
                .map(FieldDisplayConfig::getFieldName)
                .collect(Collectors.toList());

        // 创建一个Map缓存字段的反射信息，同时进行下划线到驼峰命名法转换
        Map<String, Field> fieldCache = new HashMap<>();
        for (FieldDisplayConfig fieldDisplayConfig : fieldDisplayConfigList) {
            if (fieldDisplayConfig.getIsDisplay()) {
                String camelCaseFieldName = toCamelCase(fieldDisplayConfig.getFieldName());
                try {
                    Field field = Product.class.getDeclaredField(camelCaseFieldName);
                    field.setAccessible(true);
                    fieldCache.put(camelCaseFieldName, field);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }

        // 查询自定义文本配置表,配置自定义查询字段结果
        List<CustomTextConfig> customTextConfigList = customTextConfigService.getCustomTextConfigList();
        for (CustomTextConfig textConfig : customTextConfigList) {
            result.put(textConfig.getFieldName(), textConfig.getCustomText());
        }

        // 查询产品数据
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.select(fieldNameList.toArray(new String[0]));  // 只查询需要的字段
        productQueryWrapper.eq("is_deleted", false);
        productQueryWrapper.orderByAsc("id");
        if (productSerial != null || !productSerial.equals("")){
            productQueryWrapper.eq("product_serial", productSerial);
        }
        Page<Product> productPage = productMapper.selectPage(page, productQueryWrapper);

        // 对查询结果进行遍历，根据字段配置表和自定义文本配置表进行展示
        List<Map<String, Object>> displayedProductList = productPage.getRecords().stream()
                .map(product -> {
                    Map<String, Object> displayedProduct = new HashMap<>();
                    fieldCache.forEach((fieldName, field) -> {
                        try {
                            Object value = field.get(product);
                            displayedProduct.put(fieldName, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
                    return displayedProduct;
                })
                .collect(Collectors.toList());

        // 将处理后的产品列表添加到结果中
        result.put("products", displayedProductList);
        result.put("total", productPage.getTotal());
        result.put("current", productPage.getCurrent());
        result.put("pages", productPage.getPages());

        return Result.ok(result);
    }

    @Override
    public Result findByTypeAndSerial(String productType,String productSerial) {
        //查询产品序列号产品是否已经存在
        Product byOrderNo2 = productMapper.findByTypeAndSerial(productType, productSerial);
        if (byOrderNo2 != null){
            return Result.ok(byOrderNo2);
        }else{
            return Result.fail("该产品不存在！");
        }
    }

    private static String toCamelCase(String fieldName) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = false;
        for (char c : fieldName.toCharArray()) {
            if (c == '_') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }



}
