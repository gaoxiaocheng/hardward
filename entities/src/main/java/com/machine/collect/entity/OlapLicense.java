package com.machine.collect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.sql.Blob;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author gaoyapeng
 * @since 2021-06-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OlapLicense extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医院名称
     */
    private String name;

    private byte[] file;

    /**
     * 机器码
     */
    private String code;

    private LocalDateTime createTime;

    private String ip;

    private byte[] fileTemp;


}
