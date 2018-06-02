package vorobyshko;

public class GenPass {
    public static  void main(String[]a){
        if(a.length>0)System.out.println("##############   "+new AppPasswordEncoder().encode(a[0])+"   ##############");
        else System.out.println("############## Не указан пароль ##############");
    }
}
