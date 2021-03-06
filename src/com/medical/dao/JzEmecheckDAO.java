package com.medical.dao;

import com.medical.model.JzEmecheck;
import com.medical.model.JzEmecheckExample;
import java.math.BigDecimal;
import java.util.List;

public interface JzEmecheckDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table YLJZN.JZ_EMECHECK
     *
     * @ibatorgenerated Wed Sep 30 09:34:48 CST 2009
     */
    int countByExample(JzEmecheckExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table YLJZN.JZ_EMECHECK
     *
     * @ibatorgenerated Wed Sep 30 09:34:48 CST 2009
     */
    int deleteByExample(JzEmecheckExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table YLJZN.JZ_EMECHECK
     *
     * @ibatorgenerated Wed Sep 30 09:34:48 CST 2009
     */
    int deleteByPrimaryKey(BigDecimal emecheckId);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table YLJZN.JZ_EMECHECK
     *
     * @ibatorgenerated Wed Sep 30 09:34:48 CST 2009
     */
    BigDecimal insert(JzEmecheck record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table YLJZN.JZ_EMECHECK
     *
     * @ibatorgenerated Wed Sep 30 09:34:48 CST 2009
     */
    BigDecimal insertSelective(JzEmecheck record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table YLJZN.JZ_EMECHECK
     *
     * @ibatorgenerated Wed Sep 30 09:34:48 CST 2009
     */
    List<JzEmecheck> selectByExample(JzEmecheckExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table YLJZN.JZ_EMECHECK
     *
     * @ibatorgenerated Wed Sep 30 09:34:48 CST 2009
     */
    JzEmecheck selectByPrimaryKey(BigDecimal emecheckId);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table YLJZN.JZ_EMECHECK
     *
     * @ibatorgenerated Wed Sep 30 09:34:48 CST 2009
     */
    int updateByExampleSelective(JzEmecheck record, JzEmecheckExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table YLJZN.JZ_EMECHECK
     *
     * @ibatorgenerated Wed Sep 30 09:34:48 CST 2009
     */
    int updateByExample(JzEmecheck record, JzEmecheckExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table YLJZN.JZ_EMECHECK
     *
     * @ibatorgenerated Wed Sep 30 09:34:48 CST 2009
     */
    int updateByPrimaryKeySelective(JzEmecheck record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table YLJZN.JZ_EMECHECK
     *
     * @ibatorgenerated Wed Sep 30 09:34:48 CST 2009
     */
    int updateByPrimaryKey(JzEmecheck record);
}