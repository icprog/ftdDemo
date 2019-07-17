package com.william.ftd_core.entity;

public class Conclusion {

    private UploadResult faceResult;
    private UploadResult tongueTopResult;
    private UploadResult tongueBottomResult;

    public UploadResult getFaceResult() {
        return faceResult;
    }

    public void setFaceResult(UploadResult faceResult) {
        this.faceResult = faceResult;
    }

    public UploadResult getTongueTopResult() {
        return tongueTopResult;
    }

    public void setTongueTopResult(UploadResult tongueTopResult) {
        this.tongueTopResult = tongueTopResult;
    }

    public UploadResult getTongueBottomResult() {
        return tongueBottomResult;
    }

    public void setTongueBottomResult(UploadResult tongueBottomResult) {
        this.tongueBottomResult = tongueBottomResult;
    }
}
