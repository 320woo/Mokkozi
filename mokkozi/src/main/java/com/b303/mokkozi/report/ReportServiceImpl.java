package com.b303.mokkozi.report;

import com.b303.mokkozi.board.dto.BoardDto;
import com.b303.mokkozi.entity.Board;
import com.b303.mokkozi.entity.ReportUser;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.report.dto.ReportUserDto;
import com.b303.mokkozi.report.request.ReportUserPostReq;
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
    public Page<ReportUserDto> getUserReportList(int pageIdx) {

        int size = 10;
        int page = pageIdx <= 0 ? 0 : pageIdx - 1;

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

        Page<ReportUser> pageTuts = ruRepository.findAll(pageable);
        Page<ReportUserDto> boardList = pageTuts.map(m -> new ReportUserDto(m,userRepository.findEmailById(m.getTargetId())));

        return boardList;
    }

}
