package com.trashbin.mapper;

import com.trashbin.domain.Address;
import com.trashbin.domain.ReportEntity;
import com.trashbin.domain.TrashCategory;
import com.trashbin.dto.ReportDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    default ReportEntity reportRequestPostDtoToReportEntity(ReportDto.PostDto postDto) {
        if (postDto == null) {
            return null;
        } else {
            TrashCategory trashCategory;
            if (postDto.getTrashCategory().equals("CIGARETTE")) trashCategory = TrashCategory.CIGARETTE;
            else if (postDto.getTrashCategory().equals("RECYCLE")) trashCategory = TrashCategory.RECYCLE;
            else trashCategory = TrashCategory.GENERAL;

            Address address = Address.builder()
                    .gu(postDto.getGu())
                    .roadName(postDto.getRoadName())
                    .detailAddress(postDto.getDetailAddress())
                    .installPoint(postDto.getInstallPoint())
                    .coordinateX(postDto.getCoordinateX())
                    .coordinateY(postDto.getCoordinateY())
                    .build();

            return ReportEntity.builder()
                    .trashCategory(trashCategory)
                    .nullCount(postDto.getNullCount())
                    .address(address)
                    .build();
        }
    }

    default ReportDto.ResponseDto reportEntityToReportResponseDto(ReportEntity reportEntity){
        if (reportEntity == null) {
            return null;
        } else {
            return ReportDto.ResponseDto.builder()
                    .reportId(reportEntity.getId())
                    .trashCategory(reportEntity.getTrashCategory())
                    .address(reportEntity.getAddress())
                    .nullCount(reportEntity.getNullCount())
                    .build();
        }
    }
}
