package ee.esport.spring2018.web.ticket;

import ee.esport.spring2018.web.auth.EsportClaimsHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api")
public class TicketController {

    @Resource
    private TicketService ticketService;

    @GetMapping("/ticketTypes")
    public ResponseEntity<List<TicketType>> getAllTicketTypes() {
        return new ResponseEntity<>(ticketService.getAllTypes(), HttpStatus.OK);
    }

    @PostMapping("/ticketType")
    public ResponseEntity<Void> addTicketTypes(@RequestBody TicketType type, EsportClaimsHolder claimsHolder) {
        if (!claimsHolder.get().isAdmin()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        ticketService.addType(type);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ticketType/{typeId}")
    public ResponseEntity<TicketType> getTicketType(@PathVariable int typeId) {
        return new ResponseEntity<TicketType>(ticketService.getType(typeId), HttpStatus.OK);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatus.OK);
    }

    @PostMapping("/ticket")
    public ResponseEntity<Void> buyTicket(@RequestBody Ticket ticket, HttpServletRequest request) {
        String referrer = request.getHeader(HttpHeaders.REFERER);
        ticketService.buyTicket(ticket, referrer != null ? referrer : request.getRequestURL().toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
