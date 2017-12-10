package cheanxin.web;

import cheanxin.domain.LoanLog;
import cheanxin.service.LoanLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jawinton on 17/02/09.
 */
@RestController
@RequestMapping("/loan_logs")
public class LoanLogController extends BaseController {
    @Autowired
    LoanLogService loanLogService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<LoanLog> list(@RequestParam(value = "loanId") int loanId,
                              @RequestParam(value = "fromStatus") int fromStatus,
                              @RequestParam(value = "toStatus") int toStatus) {
        return loanLogService.list(loanId, fromStatus, toStatus);
    }

}
