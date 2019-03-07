package com.oryam.howto.persistence.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.oryam.howto.persistence.jpa.meta.ItemWithSeqJpaMeta;

@Embeddable
public class ItemWithSeqJpaId implements Serializable {

    private String code;
    private Integer version;

    public ItemWithSeqJpaId() {
    }

    public ItemWithSeqJpaId(String code, Integer version) {
        this.code = code;
        this.version = version;
    }

    @Column(name = "CODE", unique = true, nullable = false, length = 52)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "VERSION", nullable = false, precision = 4, scale = 0)
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (code != null) {
            result = 31 * result + code.hashCode();
        }
        if (version != null) {
            result = 31 * result + version.intValue();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ItemWithSeqJpaId id = (ItemWithSeqJpaId) obj;
        if (code != null && version != null) {
            return code.equals(id.getCode()) && version.equals(id.getVersion());
        }

        return super.equals(obj);
    }

}
