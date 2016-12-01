package lanou.days.write;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/30,
 * otherwise, I do not know who create it either.
 */

public class TemplateFactory {
    public static String getTemplateContent(int templateNum) {
        String template = "";
        switch (templateNum) {
            case 0:template ="收入:\n" +
                    "\n" +
                    "\n" +
                    "渠道:\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "支出:\n" +
                    "\n" +
                    "\n" +
                    "方式:\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "**********************************************\n" +
                    "今日总账:\n" +
                    "\n" +
                    "\n" +
                    "小结:";
                break;
            case 1:template = "会议日期: \n"+
                    "______________________________________________\n" +
                    "会议主题: \n" +
                    "______________________________________________\n" +
                    "会议内容\n" +
                    "     1.\n" +
                    "\n" +
                    "     2.\n" +
                    "\n" +
                    "     3.\n" +
                    "\n" +
                    "     4.\n" +
                    "\n" +
                    "______________________________________________\n" +
                    "ToDo事项\n" +
                    "     ①:\n" +
                    "     ②:\n" +
                    "     ③:\n" +
                    "     ④:\n" +
                    "     ⑤:\n" +
                    "     ⑥:\n" +
                    "     ⑦:\n" +
                    "\n" +
                    "\n" +
                    "______________________________________________\n" +
                    "Days提醒您,要做的事情最优数量是3喵";
                break;
            case 2:template = "天气 ☁                            心情 (｡・`ω´･)\n" +
                    "=====================================\n" +
                    "   (´・ω・)ﾉ     每天写日记是很好的习惯喵";
                break;
        }
        return template;
    }
    public static String getTemplateTitle(int templateNum) {
        String title= "";
        switch (templateNum) {
            case 0:
                title = "的账单 ";
                break;
            case 1:
                title = "的会议记录";
                break;
            case 2:
                title = "日记";
                break;
        }
        return title;
    }
}
