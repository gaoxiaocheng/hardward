package com.machine.collect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author gaoyapeng
 * @since 2021-05-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class InspectionRecord extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private LocalDateTime creatTime;

    private String createBy;

    private String remark;

    private String status;

    private String updateBy;

    private LocalDateTime updateTime;

    private String confirmationBy;

    private LocalDateTime confirmationTime;

    private Integer isQualified;

    private String testBy;

    private LocalDateTime testTime;

    private String type;


}
