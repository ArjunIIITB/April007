package com.example.mhmsbmr;

import com.example.mhmsbmr.patient.PatientUtility;

import org.junit.Test;

public class PatientUtilityTest {
    @Test
    public void getLastVisitDataTest(){
        new PatientUtility().getLastVisitData("","", "");
    }


    @Test
    public void getPatientTest(){
        String loginToken = "eyJEZXZlbG9wZWQgQnkiOiJlLUhlYWx0aCBSZXNlYXJjaCBDZW50ZXIsIElJSVQgQmFuZ2Fsb3JlIiwiSG9zdCI6Ikthcm5hdGFrYSBNZW50YWwgSGVhbHRoIE1hbmFnZW1lbnQgU3lzdGVtIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJwcm9mZXNzaW9uIjoiTUhNU1BzeWNoaWF0cmlzdCIsInN1YiI6Ik1ITVMgU2VjdXJpdHkgVG9rZW4iLCJsYXN0TG9naW5PcmdJZCI6ImEyMWI4ODVlLTJmM2EtNDQyNS04YjViLTBkMjc0YjQyYWYyNiIsInNlc3Npb25FbmRUaW1lIjoxNTg2NDkyNjcyLCJpc3MiOiJLTUhNUyIsInNlc3Npb25TdGFydFRpbWUiOjE1ODY0NDk0NzIsInNlc3Npb25JZCI6ImVlYThmMzQwLTA5OWMtNGFlOS1hODNhLTI0MDUyODNjMzA5YiIsInVzZXJOYW1lIjoicHJhc2hhbnQiLCJvcmdVVUlEIjoiNGNjNzQyODAtZWZlNS00MDE2LWI0MWUtZjI5NDcyYTRlYzEyIiwibmJmIjoxNTg2NDQ5NDcyLCJvcmdSb2xlIjoiTUhQcm9mZXNzaW9uYWwiLCJzZXNzaW9uVG9rZW4iOiJTZXNzaW9uSWQ6MTcyLjMxLjUuMTMjcHJhc2hhbnQ6NGNjNzQyODAtZWZlNS00MDE2LWI0MWUtZjI5NDcyYTRlYzEyOk1ITVM6TUhQcm9mZXNzaW9uYWwjMTU4NjQ0OTQ3MjQ4MCMxOTc3MzY4MDA3IzM4NCIsInBlcnNvbklkIjoiOTI1ZDY3Y2QtN2QzYy00MDc4LTg5ZmItNjk2M2M0N2I0OTZhIiwidXNlclVVSUQiOiI3NzViOGMzZS02NzQyLTRiMzAtYjQ0My1jN2Q2YWE2ZWM0YWMiLCJleHAiOjE1ODY0ODU0NzIsImlhdCI6MTU4NjQ0OTQ3Mn0.FwB8cgL3c7PEd0Ns4le8ck3NeTYXg5UdfF5litk0aJk";
        String patientId = "c55dd6c4-c571-41b1-a9af-ee634d9ebefe";
        String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1586449472480#1977368007#384";

        new PatientUtility().getPatient(loginToken, patientId, sessionToken);

    }
}
