package com.william.ftd_core.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AnalyzeResultBean {

    private ArrayList<Data> list;

    public String getOpinion() {
        String opinion = "";
        Data data;
        if (list != null && list.size() > 0) {
            data = list.get(0);
//            opinion = data.getName() + ":" + data.getIntro() + data.getRemark() + data.getOpinion();
            opinion = data.getOpinion();
        }
        return opinion;
    }

    public ArrayList<Data> getDataList() {
        return list;
    }

    public void setDataList(ArrayList<Data> dataList) {
        this.list = dataList;
    }

    private static class Data {
        /**
         * code : ZYX120
         * intro : 指体内血液运行不畅所表现的证候。常表现为面色晦暗，口唇暗淡，易起褐斑，身体局部刺痛等。
         * name : 血瘀证
         * alias :
         * remark : 血循行脉中，营养及滋润全身。血瘀是体内血液运行不畅的一种状态，包括微循环不畅、血液粘稠、或血管堵塞等病理状态，是多个身体指标的集体表现。
         * 血瘀的一般表现可概括为痛、麻、紫、淤、冷、肿、硬、乏等表现形式。具体表现为体内出现瘀滞则相应部位出现淤青（紫），面色晦暗，色素沉着，或有瘀斑；口唇暗淡或紫；舌质暗、有瘀点或片状瘀斑、舌下静脉曲张；女性多痛经、闭经、延期、经量少、崩漏，或经血暗黑，有血块等。
         * <p>
         * 常见人群：
         * 1、患有心脑血管疾病的人群；
         * 2、患有慢性疾病未及时纠正，迁延3个月以上的人群；
         * 3、气虚，体内血液运行缓慢导致血瘀的一类人群；
         * 4、偏嗜生冷饮食、或久居寒湿之地，阳虚寒凉导致血瘀的一类人群；
         * 5、阴虚、湿热而热伤阴液，导致血瘀的一类人群；
         * 6、偏嗜肥甘厚味，内生痰湿导致血瘀的一类人群；
         * 7、不爱运动，或者生闷气抑郁寡欢导致气滞血瘀的一类人群。
         * opinion : 寒邪或气虚造成血液流动缓慢容易淤堵在身体器官或血管内，易引发心脑血管疾病，高血压及身体各部位疼痛等症状或疾病，女性易出现痛经，子宫肌瘤等。日常注意防寒保暖，尤其头部及手脚，禁大悲大怒，通过日常饮食及传统功法保健（如八段锦，五禽戏等）或温灸疗愈等中医之法，以畅通血脉，未病先防。
         */

        private String code;
        private String intro;
        private String name;
        private String alias;
        private String remark;
        private String opinion;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOpinion() {
            return opinion;
        }

        public void setOpinion(String opinion) {
            this.opinion = opinion;
        }
    }
}
