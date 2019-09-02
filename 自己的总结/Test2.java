import com.dahua.xray.pojo.Depart;
import com.dahua.xray.pojo.Department;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * User: Administrator
 * Date: 2019/9/3
 * Time: 0:10
 * Description:
 **/
@Data
public class Test2 {
    private static List<Depart> depart11=new ArrayList<>();

    public static void main(String[] args) {
        init();
        Depart depart = new Depart("root", "1", null, true);

//        List<Depart> collect = findChildren(new Depart()).collect(Collectors.toList());
        List<Depart> collect = findChildren2(depart);
        System.out.println(collect);

    }
    private static void init(){
        // Depart(String deptName, String deptId, String parentId, boolean childed) {
        Depart root = new Depart("root", "1", null, true);
        Depart depart1 = new Depart("部门一", "2", "1", true);
        Depart depart2 = new Depart("部门二", "3", "1", true);
        Depart depart3 = new Depart("部门一A", "4", "2", false);
        Depart depart4 = new Depart("部门二A", "5", "3", false);
        Depart depart5 = new Depart("部门二B", "6", "3", false);
        Depart depart6 = new Depart("部门二C", "7", "3", false);
        depart11.add(root);
        depart11.add(depart1);
        depart11.add(depart2);
        depart11.add(depart3);
        depart11.add(depart4);
        depart11.add(depart5);
        depart11.add(depart6);
    }
    private static Stream<Depart> findChildren(Depart parentDepart) {
        return depart11.stream()
                .filter(d -> Objects.equals(parentDepart.getDeptId(), d.getParentId()))
                .flatMap(d -> Stream.concat(Stream.of(d), findChildren(d)));
    }

    private static List<Depart> findChildren2(Depart parentDept) {
        // 查找所有下级部门
        List<Depart> childrenList = depart11.stream()
                .filter(d -> Objects.equals(parentDept.getDeptId(), d.getParentId()))
                .collect(Collectors.toList());

        List<Depart> tempList = new ArrayList<>();
        // 遍历所有下级
        childrenList.forEach(d -> {
            if (!d.isChilded()) {
                tempList.add(d);
            }
            tempList.addAll(findChildren2(d));
        });
        return tempList;
    }
	
	    private static List<Depart> findChildren3(Depart parentDept) {
        List<Depart> res = new ArrayList<>();
        Queue<Depart> q=new LinkedList<>();
        q.add(parentDept);

        while (!q.isEmpty()) {
            Depart dept = q.poll();
            if (!dept.isChilded()) {
                res.add(dept);
            } else {
                getChild(dept,q);
            }

        }

        return res;
    }

    private static void getChild(Depart parentDept,Queue queue) {
        List<Depart> collect = depart112.stream()
                .filter(d -> Objects.equals(parentDept.getDeptId(), d.getParentId()))
                .collect(Collectors.toList());
        queue.addAll(collect);
    }
}
