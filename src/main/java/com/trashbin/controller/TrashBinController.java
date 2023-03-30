package com.trashbin.controller;

import com.trashbin.dto.TrashBinDto;
import com.trashbin.service.TrashBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("trashbin")
public class TrashBinController {
    private final TrashBinService trashBinService;
    @PostMapping("/create")
    public ResponseEntity<?> createTrashBin(@RequestBody TrashBinDto.PostDto postDto){
        return ResponseEntity.ok().body(trashBinService.createTrashBin(postDto));
    }

//    @GetMapping("/get")
//    public ResponseEntity<?> getTrashBin(@RequestParam Long trashBinId){
//        return ResponseEntity.ok().body(trashBinService.);
//    }

    @PatchMapping("/modify")
    public ResponseEntity<?> modifyTrashBin(@RequestBody TrashBinDto.PatchDto patchDto){
        return ResponseEntity.ok().body(trashBinService.updateTrashBin(patchDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTrashBin(@RequestParam Long trashBinId){
        trashBinService.deleteTrashBin(new TrashBinDto.DeleteDto(trashBinId));
        return ResponseEntity.ok().body("deleted trashBinId:"+trashBinId);
    }
}