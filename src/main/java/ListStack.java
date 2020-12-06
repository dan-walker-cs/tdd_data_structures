public class ListStack<T> {

    private ListNode top;

    private class ListNode<T> {
        private T data;
        private ListNode next;

        ListNode() {
            this.data = null;
            this.next = null;
        }

        ListNode(T data) {
            this.data = data;
            this.next = null;
        }
    }

    ListStack() {
        this.top = new ListNode();
    }

    public void push(T data) {
        if (top.next == null) {
            top.next = new ListNode(data);
        }
    }

    public String toString() {
        if (top.next == null) {
            return "Stack Empty";
        }
        String template = "";
        ListNode temp = top.next;

        template += "top: ";
        while(temp.next != null) {
            template += temp.data;
            template += "\nv\n";
        }

        template += "null";
        return template;
    }
}