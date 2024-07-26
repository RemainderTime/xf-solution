package cn.xf.asyncone.controller;

import cn.xf.asyncone.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName cn.xf.asyncone
 *
 * @author remaindertime
 * @className UserController
 * @date 2024/7/26
 * @description
 */
@RestController
public class UserController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/manufacturing")
    public Boolean manufacturing(String userId) {
        return  purchaseService.manufacturing(userId);
    }
}
