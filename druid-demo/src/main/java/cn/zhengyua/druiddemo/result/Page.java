package cn.zhengyua.druiddemo.result;

import cn.zhengyua.druiddemo.param.PageParam;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库分页封装
 *
 * @author tudou
 * @date 2020/1/1117:00
 */

@Data
public class Page<E> implements Serializable {

    private static final long serialVersionUID = -2020350783443768083L;

    //当前页数
    private int currentPage = 1;
    //总页数
    private long totalPage;
    //总记录数
    private long totalNumber;
    //数据集
    private List<E> list;

    public static Page NULL = new Page(0, 0, 15, new ArrayList());

    public Page() {
        super();
    }

    public Page(int beginLine, long totalNumber, int pageSize, List<E> list) {
        super();
        this.currentPage = beginLine / pageSize + 1;
        this.totalNumber = totalNumber;
        this.list = list;
        this.totalPage = totalNumber % pageSize == 0 ? totalNumber
                / pageSize : totalNumber / pageSize + 1;
    }

    public Page(PageParam pageParam, long totalNumber, List<E> list){
        super();
        this.currentPage = pageParam.getCurrentPage();
        this.totalNumber = totalNumber;
        this.list = list;
        this.totalPage = totalNumber % pageParam.getPageSize() == 0 ? totalNumber
                / pageParam.getPageSize() : totalNumber / pageParam.getPageSize() + 1;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalNumber=" + totalNumber +
                ", list=" + list +
                '}';
    }

}
