package com.vivsoft.service;


import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vivsoft.model.Employee;

/**
 * 
 * 1. request refresh token
 * http://localhost:8080/security/oauth/token?grant_type=password&client_id=my-trusted-client&username=admin&password=password
 * 
 * 2. 
 * http://localhost:8080/security/oauth/token?grant_type=refresh_token&client_id=my-trusted-client&refresh_token=#
 * 
 * 3.
 * http://localhost:8080/security/employee/list?access_token=#
 */

@Controller
@RequestMapping("/employee")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EmployeeService {

	static Set Employees;

    static {
        Employees = new HashSet();
        Employee foobar = null;
        for (int i = 0; i < 10; i++) {
            double sal = new SecureRandom().nextInt(400)*500;
            foobar = new Employee(i, "Employee " + i, sal );
            Employees.add(foobar);
        }
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public Employee getFoobar(@PathVariable int employeeId) {
        Iterator X = Employees.iterator();
        while (X.hasNext()) {
            Employee f = (Employee) X.next();
            if (f.getId() == employeeId) return f;
        }
        return null;
    }

    @RequestMapping(value = "/htmllist", method = RequestMethod.GET, headers = "Accept=text/html", produces = {"text/html"})
    @ResponseBody
    public String getFoobarListHTML() {
        String retVal = "<html><body><table border=1>";
        Iterator X = Employees.iterator();
        while (X.hasNext()) {
            Employee f = (Employee) X.next();
            retVal += "<tr><td>" + f.getId() + "</td><td>" + f.getName() + "</td><td>" + f.getSalary() + "</td></tr>";
        }
        retVal += "</table></body></html>";

        return retVal;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public Set getFoobarList() {
        return Employees;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public Set getFoobars() {
        return Employees;
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT, headers = "Accept=application/json", produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public Employee editFoobar(@RequestBody Employee foobar, @PathVariable int employeeId) {
        Iterator X = Employees.iterator();
        while (X.hasNext()) {
            Employee f = (Employee) X.next();
            if (employeeId == f.getId()) {
                f.setId(foobar.getId());
                f.setName(foobar.getName());
                return f;
            }
        }
        return null;
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public boolean deleteFoobar(@PathVariable int employeeId) {
        System.out.println("Delete call.");
        Iterator fooIterator = Employees.iterator();
        while (fooIterator.hasNext()) {
            Employee foobar = (Employee) fooIterator.next();
            System.out.println(foobar);
            if (foobar.getId() == employeeId) {
                fooIterator.remove();
                return true;
            }
        }
        return false;
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public boolean createFoobar(@RequestBody Employee employee) {
        return Employees.add(employee);
    }
}
