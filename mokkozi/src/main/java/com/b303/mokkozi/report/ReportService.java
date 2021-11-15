package com.b303.mokkozi.report;

import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.report.dto.ReportUserDto;
import com.b303.mokkozi.report.request.ReportUserPostReq;
import com.b303.mokkozi.report.request.ReportUserUpdateReq;
import org.springframework.data.domain.Page;

public interface ReportService {

    void createUserReport(User user, ReportUserPostReq rcpr);

    Page<ReportUserDto> getUserReportList(int page, String result);

    ReportUserDto getUserReport(Long reportId);

    void updateUserReport(ReportUserUpdateReq ruur);
}
