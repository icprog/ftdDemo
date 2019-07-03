package com.william.ftd_core.exception;

public class FtdException extends Throwable {

//    private static final String

    private int code;
    private String msg;

    public FtdException(int code) {
        this.code = code;
        switch (code) {
            case 100090:
                msg = "图像太大";
                break;
            case 100091:
                msg = "图像太小";
                break;
            case 100109:
                msg = "面部图片识别不到人脸";
                break;
            case 100110:
            case 100112:
                msg = "面部左右亮度不均匀，请保证光线均匀后重新拍照";
                break;
            case 100111:
            case 100117:
                msg = "为确保结果准确，请重新拍照";
                break;
            case 100113:
                msg = "舌头太小,请完整伸出舌头，重新拍照";
                break;
            case 100114:
                msg = "人脸太大或太小,请将面部放在虚线和实线之间重新拍照";
                break;
            case 100118:
                msg = "背景左右亮度不均匀，请保证光线均匀后重新拍照";
                break;
            case 100119:
                msg = "人脸太大，请重新拍照";
                break;
            case 100120:
                msg = "将面部放在虚线和实线之间重新拍照";
                break;
            case 100121:
                msg = "照片不对焦，请重新拍照";
                break;
            case 100122:
                msg = "舌头太小";
                break;
            case 100123:
                msg = "没检测到舌头";
                break;
            case 100011:
                msg = "100011=参数错误";
                break;
            case 100061:
                msg = "舌头太小，请完整伸出舌头，注意拍照距离不要过远";
                break;
            case 100062:
                msg = "面象分割失败";
                break;
            case 100063:
                msg = "舌象分割失败";
                break;
            case 100092:
                msg = "图像格式不支持";
                break;
            case 99994:
                msg = "辨证论治处理异常";
                break;
            case 99995:
                msg = "查询数据不存在";
                break;
            case 99996:
                msg = "算法数据处理异常";
                break;
            case 99997:
                msg = "数据查询异常";
                break;
            case 99998:
                msg = "数据操作超时";
                break;
            case 100115:
                msg = "亲，您的网络好像有问题哦";
                break;
            default:
                msg = "网络不稳定，请稍后重试";
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
