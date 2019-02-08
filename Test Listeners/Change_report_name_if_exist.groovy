import org.apache.commons.io.FileUtils

import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestSuiteContext



class Change_report_name_if_exist {
	
	@AfterTestSuite
	def printInfo(TestSuiteContext testSuiteContext) {

		
		String filePath = '/katalon/katalon/source/report'
	
		String mainReportFolder = filePath
		if (!(new File(mainReportFolder).exists())) {
			new File(mainReportFolder).mkdirs()
		}
		
		Date today = new Date()
		String todaysDate = today.format('yyyy-MM-dd HH_mm_ss')
	
		FileUtils.moveFile(
			FileUtils.getFile("/katalon/katalon/source/report/report.html"),
			FileUtils.getFile('/katalon/katalon/source/report/report_'+todaysDate+'.html'));
		
		FileUtils.moveFile(
			FileUtils.getFile("/katalon/katalon/source/report/JUnit_Report.xml"),
			FileUtils.getFile('/katalon/katalon/source/report/JUnit_Report_'+todaysDate+'.xml'));
		

}
}