package cn.xf.asyncone.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * packageName cn.xf.asyncone.service
 *
 * @author remaindertime
 * @className PurchaseService
 * @date 2024/7/26
 * @description
 */
@Service
public interface PurchaseService {

    Boolean manufacturing(String idCard);
}
