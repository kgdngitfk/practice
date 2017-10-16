/**
 * Created by wuhuaiqian on 17-6-28.
 */
public class Area {
    /**
     * 编码
     */
    private Integer code;
    /**
     * 名称
     */
    private String name;
    /**
     * 上级区域编码
     */
    private Integer parentCode;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentCode() {
        return parentCode;
    }

    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }
}
