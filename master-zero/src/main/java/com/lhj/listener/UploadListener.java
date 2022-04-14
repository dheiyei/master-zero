package com.lhj.listener;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UploadListener {

//    private final OilService oilService;
//    private final UnitService unitService;
//
//    //异步监听器
//    @Async
//    @EventListener
//    public void dualEven(UploadEvent event) throws IOException {
//        if (event.getUploadEnum() == UploadEnum.OIL){
//            oilService.importSqlFile(event);
//        }else if (event.getUploadEnum() == UploadEnum.UNIT){
//            unitService.importSqlFile(event.getInputStream());
//        }
//    }
}
