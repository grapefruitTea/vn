package com.vn.demo.practce.thread;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class SortDemo {
    public static void main(String[] args) {
        System.out.println("start main.");
        // 4个线程分别读取文件，做排序
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable callableA = () -> getSortByFile("D:\\Nothing\\sort\\A.txt");
        Callable callableB = () -> getSortByFile("D:\\Nothing\\sort\\B.txt");
        Callable callableC = () -> getSortByFile("D:\\Nothing\\sort\\C.txt");
        Callable callableD = () -> getSortByFile("D:\\Nothing\\sort\\D.txt");

        Future submitA = executorService.submit(callableA);
        Future submitB = executorService.submit(callableB);
        Future submitC = executorService.submit(callableC);
        Future submitD = executorService.submit(callableD);

        List<Integer> listA = null;
        List<Integer> listB = null;
        List<Integer> listC = null;
        List<Integer> listD = null;
        try {
            listA = (List<Integer>) submitA.get();
            listB = (List<Integer>) submitB.get();
            listC = (List<Integer>) submitC.get();
            listD = (List<Integer>) submitD.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("#################");
        executorService.shutdown();

        // 完成后，利用priorityQueue做归并
        mergeSort(listA,listB,listC,listD);

        System.out.println("end main.");
    }

    private static void mergeSort(List<Integer> listA, List<Integer> listB, List<Integer> listC, List<Integer> listD) {
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("A", listA);
        map.put("B", listB);
        map.put("C", listC);
        map.put("D", listD);
        int size = map.size();

        PriorityQueue<Elements> queue = new PriorityQueue<>(size);
        // 向quene设置初始值
        for (String key : map.keySet()) {
            queue.offer(new Elements(map.get(key).get(0), key, 0));
        }
        int i = 0;
        Elements temp = null;
        while ((temp = queue.poll()) != null) {
            // 输出queue的值
            System.out.print(temp.getValue()+"\t");
            i++;
            if (i % 5 == 0) {
                System.out.println();
            }
            if (temp.getWhichIndex() + 1 < map.get(temp.getWhichObject()).size()) {
                queue.offer(new Elements(map.get(temp.getWhichObject()).get(temp.getWhichIndex() + 1),
                        temp.getWhichObject(), temp.getWhichIndex() + 1));
            }
        }
        System.out.println();
    }


    private static List<Integer> getSortByFile(String s) {
        List<Integer> result = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(s)));
            String str;
            while (null != (str = br.readLine())) {
                result.add(Integer.valueOf(str));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Collections.sort(result);
        return result;
    }



}
class Elements implements Comparable<Elements>{
    private int value;
    private String whichObject;
    private int whichIndex;

    public Elements(int value, String whichObject, int whichIndex) {
        this.value = value;
        this.whichObject = whichObject;
        this.whichIndex = whichIndex;
    }


    /**
     * 如果指定的数与参数相等返回0。
     *
     * 如果指定的数小于参数返回 -1。
     *
     * 如果指定的数大于参数返回 1。
     * @param e
     * @return
     */
    @Override
    public int compareTo(Elements e) {
        return Integer.compare(value, e.getValue());
    }

    public int getValue() {
        return value;
    }



    public String getWhichObject() {
        return whichObject;
    }


    public int getWhichIndex() {
        return whichIndex;
    }

}