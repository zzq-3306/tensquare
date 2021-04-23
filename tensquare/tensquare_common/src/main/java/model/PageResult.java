package model;

import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/22 17:33
 * @Description  返回分页结果
 */
public class PageResult<T> {

    private Long total;

    private List<T> rows;

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {
    }
}
