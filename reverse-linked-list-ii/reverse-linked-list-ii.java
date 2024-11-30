/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode iter = dummy;

        for(int i=1; i<left; i++) {
            iter = iter.next;
        }

        ListNode leftNode = iter.next;
        ListNode rightNode = leftNode;

        for(int i=left; i<right; i++) {
            rightNode = rightNode.next;
        }

        ListNode rightNext = rightNode.next;
        reverse(rightNode, leftNode);

        iter.next = rightNode;
        leftNode.next = rightNext;
        return dummy.next;
    }

    public void reverse(ListNode rightNode, ListNode leftNode) {
        ListNode prev = rightNode.next;
        ListNode curr = leftNode;
        while(curr != rightNode) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        curr.next = prev;
    }
}