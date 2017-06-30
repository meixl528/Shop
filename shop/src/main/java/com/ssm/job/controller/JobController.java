package com.ssm.job.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssm.job.dto.JobCreateDto;
import com.ssm.job.dto.JobData;
import com.ssm.job.service.IQuartzService;

/**
 * 测试 job
 * @author meixl
 *
 */
@Controller
public class JobController {

	@Autowired
	private IQuartzService quartzService;		
			
	@RequestMapping(value = "/create")
	public String createJob(JobCreateDto jobCreateDto, BindingResult result,
			HttpServletRequest request) throws Exception {
		jobCreateDto.setJobName("test2");
		jobCreateDto.setJobGroup("DEFAULT");
		jobCreateDto.setJobClassName("com.ssm.job.jobs.NewJob");
		
		jobCreateDto.setTriggerGroup(jobCreateDto.getJobGroup());
		jobCreateDto.setTriggerName(jobCreateDto.getJobName() + "_trigger");
		jobCreateDto.setTriggerType("SIMPLE");
		
		jobCreateDto.setStart(new Date(2017,3,20));
		jobCreateDto.setEnd(new Date(2017, 3, 30));
		jobCreateDto.setRepeatCount("5");
		jobCreateDto.setRepeatInterval("180");
		List<JobData> jobDatas = new ArrayList<>();
		JobData job = new JobData();
		job.setName("job_internal_notification");
		job.setValue("false");
		jobDatas.add(job);
		
		job.setName("job_internal_emailAddress");
		job.setValue("");
		jobDatas.add(job);
		jobCreateDto.setJobDatas(jobDatas);
		System.out.println("jobCreateDto = " + jobCreateDto.toString());
		quartzService.createJob(jobCreateDto);
		return "welcome.jsp";
	}

}
