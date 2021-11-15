package com.b303.mokkozi.report;

import com.b303.mokkozi.entity.ReportUser;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.report.dto.ReportUserDto;
import com.b303.mokkozi.report.request.ReportUserPostReq;
import com.b303.mokkozi.report.request.ReportUserUpdateReq;
import com.b303.mokkozi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportUserRepository ruRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUserReport(User user, ReportUserPostReq rcpr) {

        userRepository.findById(rcpr.getTargetId()).orElseThrow(() -> new NoSuchElementException("not found"));

        ReportUser report = new ReportUser();
        report.setContent(rcpr.getContent());
        report.setResult("대기중");
        report.setUser(user);
        report.setTargetId(rcpr.getTargetId());
        ruRepository.save(report);
    }

    @Override
    public Page<ReportUserDto> getUserReportList(int pageIdx, String result) {

        int size = 10;
        int page = pageIdx <= 0 ? 0 : pageIdx - 1;

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDate"));

        if(result.equals("")){
            Page<ReportUser> pageTuts = ruRepository.findAll(pageable);
            Page<ReportUserDto> reportList = pageTuts.map(m -> new ReportUserDto(m,ruRepository.findEmailById(m.getTargetId())));
            return reportList;
        }else{
            Page<ReportUser> pageTuts = ruRepository.findByResult(pageable,result);
            Page<ReportUserDto> reportList = pageTuts.map(m -> new ReportUserDto(m,ruRepository.findEmailById(m.getTargetId())));
            return reportList;
        }
    }

    @Override
    public ReportUserDto getUserReport(Long reportId) {
        ReportUser report = ruRepository.findById(reportId).orElseThrow(() -> new NoSuchElementException("not found"));
        ReportUserDto reportDto = new ReportUserDto(report,ruRepository.findEmailById(report.getTargetId()));
        return reportDto;
    }

    @Override
    public void updateUserReport(ReportUserUpdateReq ruur) {
        ReportUser report = ruRepository.findById(ruur.getReportId()).orElseThrow(() -> new NoSuchElementException("not found"));
        report.setResult(ruur.getResult());
        ruRepository.save(report);
    }

}
