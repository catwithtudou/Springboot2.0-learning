package cn.zhengyua.druiddemo.param;

import lombok.Data;

/**
 * 数据库分页
 *
 * @author tudou
 * @date 2020/1/1116:40
 */


@Data
public class PageParam {

    //起始行
    private int beginLine;
    private Integer pageSize = 3;
    // 当前页
    private Integer currentPage=0;

    @Override
    public String toString() {
        return "PageParam{" +
                "beginLine=" + beginLine +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                '}';
    }
}
