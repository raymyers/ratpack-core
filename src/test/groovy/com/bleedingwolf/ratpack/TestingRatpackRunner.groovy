package com.bleedingwolf.ratpack

import java.io.File;
import org.mortbay.jetty.Server
import org.mortbay.jetty.servlet.Context
import org.mortbay.jetty.servlet.ServletHolder

class MyRatpackRunner  {
	static void main(args) {
		def runner = new MyRatpackRunner()
		def script = new File("src/test/resources/test-app.groovy")
		runner.run(script)
	  }
	
	RatpackApp app = new RatpackApp()
	
	  void run(File scriptFile) {
		app.prepareScriptForExecutionOnApp(scriptFile)
		app.config.templateRoot = 'src/test/resources/templates'
	
		// Runs this RatpackApp in a Jetty container
		def servlet = new RatpackServlet()
		servlet.app = app
	
		app.logger.info('Starting Ratpack app with config:\n{}', app.config)
	
		def server = new Server(app.config.port)
		def root = new Context(server, "/", Context.SESSIONS)
		root.addServlet(new ServletHolder(servlet), "/*")
		println "starting server"
		server.start()
		println "done starting server"
	  }
}
