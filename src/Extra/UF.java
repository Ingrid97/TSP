package Extra;

public class UF {
    private int[] qf;
    private int len;

    public UF(int n){
        qf = new int[n];
        for (int i = 0; i < n; i++) {
            qf[i] = i;
        }
        len = n;
    }

    public boolean connected(int p, int q){
        return qf[p] == qf[q];
    }

    public void connect(int p, int q){
        int cur = qf[p];
        for (int i = 0; i < len; i++)
            if (qf[i] == cur)
                qf[i] = qf[q];
    }

    @Override
    public String toString(){
        String s = "[";
        for (int i = 0; i < len; i++) {
            if(i != len-1)
                s += qf[i] + ", ";
            else
                s += qf[i];
        }
        s += "]";
        return s;
    }

    public int[] getList(){
        return qf;
    }
}
