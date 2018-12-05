package edu.iot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.iot.common.exception.PasswordMissmatchException;
import edu.iot.common.model.TravelReply;
import edu.iot.common.service.TravelReplyService;

@RestController
@RequestMapping("/travel/reply/{travelId}")
public class ReplyController {

	@Autowired
	TravelReplyService service;

	// 정상 처리 응답
	public <T> ResponseEntity<T> getResult(T t) {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=UTF-8");
		return new ResponseEntity<T>(t, headers, HttpStatus.OK);
	}

	/*// 에러 처리 응답
	public <T> ResponseEntity<T> handleError(Exception e) {
		e.printStackTrace();
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=UTF-8");
		// body 에 1 지정해줘도 됨
		return new ResponseEntity<T>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<TravelReply>> list(@PathVariable long travelId) throws Exception{
		List<TravelReply> list = service.getList(travelId);
		return getResult(list);
		
	}
	
	@RequestMapping(value="/{replyId}", method=RequestMethod.GET)
	public ResponseEntity<TravelReply> replyId(@PathVariable long replyId) throws Exception{
		TravelReply reply = service.findById(replyId);
		return getResult(reply);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TravelReply> create(@RequestBody TravelReply reply) throws Exception{
		// 데이터가 json 으로 전송되므로 @RequestBody 사용
		TravelReply r = service.create(reply);
		return getResult(r);
		
	}

	@RequestMapping(value = "/{replyId}", method = RequestMethod.PUT)
	public ResponseEntity<TravelReply> update(@RequestBody TravelReply reply) throws Exception{
		TravelReply r = service.update(reply);
		return getResult(r);
		
	}

	@RequestMapping(value = "/{replyId}", method = RequestMethod.DELETE)
	public ResponseEntity<TravelReply> delete(TravelReply reply) throws Exception{
		// password 가 쿼리 파라미터로 전송됨
		// @RequestBody 사용하지 않음
		service.delete(reply);
		return getResult(reply);
		
	}
	
	@ExceptionHandler(PasswordMissmatchException.class)
	public ResponseEntity handlePasswordMissmatchException
										(PasswordMissmatchException e) {
		return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException
										(PasswordMissmatchException e) {
		return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("서버에서 일시적 장애가 발생했습니다");
					//.body(e.getMessage());
	}
}
