package com.sii.promoCodes.Repositories;

import com.sii.promoCodes.Models.PromoCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {
    PromoCode findByCode(String code);
}
//@Repository
//public class PromoCodeRepository
////        extends CrudRepository<PromoCode, String>
//{
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    public List<PromoCode> getAll(){
//        return jdbcTemplate.query("SELECT code, expirationDate, discountAmount, currency, maxUsages FROM promoCodes",
//                BeanPropertyRowMapper.newInstance(PromoCode.class));
//
//    }
//}

