package linked.stack;

/*
    @author: kang wu
    @description: 此代码基于之前实现的链表堆栈，实现中缀表达式转换成后缀表达式。
    @date: 2021.7.28
    @revison: None

*/

public class ExpreConv {
    private char[] infix;
    //标志位为1是数字，标志位为0是符号
    private boolean[] infix_flag;
    private char[] suffix;
    private int[] suffix_flag;
    private int len;
    public ExpreConv(char[] infix, boolean[] infix_flag){
        this.infix = infix;
        this.infix_flag = infix_flag;
        this.len = infix.length;
        this.suffix = new char[infix.length];
        this.suffix_flag = new int[infix.length];
    }

    public int compare(char a, char b){
        //a 给定输入，给定输入的左括号优先级高
        //b 堆栈中的数值，堆栈中的左括号优先级低
        int level_a = 0;
        int level_b = 0;
        //a的优先级
        if(a=='+' || a == '-') level_a=2;
        else if(a=='*' || a == '/') level_a=4;
        else if(a == '(') level_a=4;
        else if(a == ')') level_a = 0;
        //b的优先级
        if(b == '(') level_b=0;
        else if(b=='+' || b == '-') level_b=1;
        else if(b=='*' || b == '/') level_b=3;

        if(level_a>level_b) return 1;
        else if(level_a<level_b) return 0;
        //else if(level_a == level_b) return 2;
        return 2;
    }

    public void get_suffix_exp(){
        LinkedStack<Character> stack  = new LinkedStack<Character>();
        int j = 0;
        for(int i=0;i< this.len;i++){
            //如果是数字直接输出
            if(this.infix_flag[i]){
                this.suffix[j] = this.infix[i];
                this.suffix_flag[j] = 1;
                j = j + 1;
            }
            //如果是字符，判断如何放入堆栈
            else{
                //堆栈为空
                if(stack.get_len() == 0){
                    stack.push(this.infix[i]);
                }
                else{
                    // 循环判断和栈顶元素比较的优先级,优先级低的入栈
                    char res = stack.pop();
                    while(compare(this.infix[i], res)==0 && stack.get_len()!=0){
                        this.suffix[j] = res;
                        this.suffix_flag[j] = 0;
                        j = j + 1;
                        res = stack.pop();
                    }
                    if(compare(this.infix[i], res)==1){
                        stack.push(res);
                        stack.push(this.infix[i]);
                        this.suffix_flag[j] = 0;
                        //j = j + 1;
                    //else if(compare(this.infix[i], res)==2)
                    }

                }
            }
        }
        int stack_len = stack.get_len();
        for(int i=0;i< stack_len;i++){
            this.suffix[j] = stack.pop();
            j = j+1;
        }

        this.len = j;
    }

    public void show_results(){
        System.out.println("VALUE:");
        System.out.println(infix);
//        System.out.println("FLAG:");
//        System.out.println(infix_flag);
        System.out.println("\n");

        System.out.println("VALUE:");
        for(int i=0; i<this.len;i++){
            System.out.println(suffix[i]);
        }

//        System.out.println("FLAG:");
//        System.out.println(suffix_flag);
    }

    public static void main(String[] args){
        char[] infix_exp = {'1','*','2', '-', '3', '+', '(', '5','-','1',')'};
        boolean[] flags = {true,false,true,false,true,false,false,true,false,true,false};
        ExpreConv expre = new ExpreConv(infix_exp, flags);

        expre.get_suffix_exp();
        expre.show_results();
    }




}
