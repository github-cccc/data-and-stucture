package com.githubzhu.linkedlist;

import javax.sound.midi.Soundbank;
import java.util.Stack;

/**
 * @Author: github_zhu
 * @Describtion:
 * @Date:Created in 2020/6/25 17:52
 * @ModifiedBy:
 */
public class LInkedListDemo {
    public static void main(String[] args) {
        //测试
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        ////创建链表
        //SingleLinkedList singleLinkedList = new SingleLinkedList();
        //singleLinkedList.add(heroNode1);
        //singleLinkedList.add(heroNode1);
        //singleLinkedList.add(heroNode2);
        //singleLinkedList.add(heroNode3);
        //singleLinkedList.add(heroNode4);


        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add2(heroNode1);
        //singleLinkedList.add2(heroNode1);
        singleLinkedList.add2(heroNode4);
        singleLinkedList.add2(heroNode2);
        singleLinkedList.add2(heroNode3);
        //显示
        singleLinkedList.list();

        singleLinkedList.reverseLinkedList(singleLinkedList.getHead());
        singleLinkedList.list();
        System.out.println("__________________");

        singleLinkedList.reversePrint(singleLinkedList.getHead());

    }
}

class SingleLinkedList {

    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑标号顺序时
    //找到当前节点的最后节点，将这个节点的next 指向新的的节点
    public void add(HeroNode heroNode) {
        //因为节点头不能动，因此需要辅助变量 temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                System.out.print("1--");
                break;
            }
            //如果没有找到最后，将temp后移
            System.out.print("2--");
            temp = temp.next;
        }
        //当退出while 时 temp 就指向链表的最后
        //将这个节点的next 指向新的节点
        temp.next = heroNode;
    }

    public void add2(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;//英雄存在标志位
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("英雄 %d 已经存在,不能加入",heroNode.no);

        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //更新链表
    public void update(HeroNode newHeroNode) {
        //判断是否为为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到修改的节点，根据弄
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//该节点是否存在
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表|
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.no = newHeroNode.no;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到%d 英雄，不能更改",newHeroNode.no);
        }

    }

    //删除元素
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("%d 英雄不存在",no);
        }
    }

    //反转
    public void reverseLinkedList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助指针（变量） 帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点的【cur】 的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来链表，每遍历一个放在新的链表最前端

        while (cur != null) {
                next = cur.next;
                cur.next = reverseHead.next;//将新的元素添加到新链表的做最前端
                reverseHead.next = cur;
                cur = next;
            }
            //将head。.next 执行reverseHead.next ，实现反转
            head.next = reverseHead.next;

    }

    //反转打印
    public void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }

        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //显示遍历链表
    public void list() {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动 因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表尾部
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将输temp 后移，
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}