import java.util.List;
public class App {
    private ItemRepository itemRepository;
    private SalesPromotionRepository salesPromotionRepository;

    public App(ItemRepository itemRepository, SalesPromotionRepository salesPromotionRepository) {
        this.itemRepository = itemRepository;
        this.salesPromotionRepository = salesPromotionRepository;
    }

    public String bestCharge(List<String> inputs) {
        int ITEM0001Price = 18;
        int ITEM0013Price = 6;
        int ITEM0022Price = 8;
        //TODO: write code here
        int ITEM0001=0;//黄焖鸡数量
        int ITEM0013=0;//肉夹馍数量
        int ITEM0022=0;//凉皮数量
        for(int i=0;i<inputs.size();i++){
            System.out.println(inputs.get(i));
            String [] temp = inputs.get(i).split("x");
            int s = Integer.parseInt(temp[1].trim());
            if(temp[0].trim().equals("ITEM0001")){
                ITEM0001=s;
            }else if(temp[0].trim().equals("ITEM0013")){
                ITEM0013=s;
            }else if(temp[0].trim().equals("ITEM0022")){
                ITEM0022=s;
            }

        }
        System.out.println("ITEM0001="+ITEM0001+"--ITEM0013="+ITEM0013+"--ITEM0022="+ITEM0022);
        int price1 = ITEM0001Price*ITEM0001+ITEM0013Price*ITEM0013+ITEM0022Price*ITEM0022;
        int price2=0;
        int price3=0;
        if (price1>=30) price3 = price1-6;
        if(ITEM0001>0||ITEM0022>0){
            price2 = ITEM0001Price*ITEM0001/2+ITEM0013*ITEM0013Price+ITEM0022*ITEM0022Price/2;
        }
        System.out.println("price1="+price1+"----"+"price2="+price2+"----price3="+price3);

        String result ="============= 订餐明细 =============\n";
        if (ITEM0001>0)
              result+=  "黄焖鸡 x "+ITEM0001+" = "+(ITEM0001*ITEM0001Price)+"元\n";
            if (ITEM0013>0)
             result+=  "肉夹馍 x "+ITEM0013+" = "+(ITEM0013*ITEM0013Price)+"元\n" ;
              if (ITEM0022>0) result+= "凉皮 x "+ITEM0022+" = "+ITEM0022*ITEM0022Price+"元\n";
              result+=  "-----------------------------------\n";

              int minPay=0;
              if (price1!=0&&price2!=0&&price3!=0){
                  minPay = Minimum(price1,price2,price3);
              }else if (price3!=0&&price2==0){
                  if (price1>price3){
                      minPay=price3;
                  }else minPay=price1;
              }else if (price2!=0&&price3==0){
                  if (price1>price2) minPay=price2;
                  else minPay=price1;
              }else minPay=price1;
        System.out.println("你花费的最少钱："+minPay);
        if(minPay==price1){
                    result+="总计："+price1+"元" +"\n===================================";
        }
        if((price1!=price3)&&(minPay==price3)){
            result+="使用优惠:\n" +
                    "满30减6元，省"+(price1-price3)+"元\n" +  "-----------------------------------\n" +
                    "总计："+price3+"元\n" +
                    "===================================";
        }
        if(minPay==price2){
            result+="使用优惠:\n" +
                    "指定菜品半价(黄焖鸡，凉皮)，省"+(price1-price2)+"元\n" +
                    "-----------------------------------\n" +
                    "总计："+price2+"元\n" +
                    "===================================";
        }
        System.out.println(result);
        return result;
    }

    int Minimum(int num1,int num2,int num3)
    {

        int min = (num1 < num2) ? num1 : num2;
        min = (min < num3) ? min : num3;

        return min;
    }
}
