# Salesforce Login Test Plan

## 1. Test Plan ID and Title

- **Plan ID:** TP-SF-LOGIN-001
- **Title:** Salesforce Login UI Test Plan
- **Status:** Draft for review
- **Scope:** One valid-login scenario and one invalid-password scenario

## 2. Objective and References

### Objective

Define a focused, repeatable UI test approach for the Salesforce login flow using Chrome, covering authentication with valid credentials and rejection of an incorrect password. This document is a test plan; it does not claim that either scenario has been executed.

### References

- Salesforce login URL supplied in the source prompt: `https://login.salesforce.com/?locale=in`
- `Selenium framework/01_RICE_POT_Prompt.md`
- `Selenium framework/04_RICE_POT_Generic_QA_Template.md`, Profile B and Profile D
- `Selenium framework/pom.xml` and `Selenium framework/testng.xml` for the generated automation stack

The source prompt supplies example XPath values, but they have not been confirmed against the current login DOM. A browser inspection showed the username-first login view and Remember Me control; it did not confirm the password or error elements.

## 3. In Scope and Out of Scope

### In scope

- Open the supplied Salesforce login URL in Chrome.
- Submit valid credentials for an approved active test account and verify an agreed authenticated state.
- Submit the same approved test account with an incorrect password and verify authentication is denied.
- Exercise these two scenarios through the existing Selenium, Java, Maven, and TestNG project.

### Out of scope

- Remember Me behavior, despite the control being reported in the source prompt.
- Password reset, registration, MFA/SSO workflows, account lockout testing, and custom-domain flows.
- Performance, security penetration, accessibility, and broad browser compatibility testing.
- Additional login input permutations beyond the two agreed scenarios.

This two-scenario scope limits coverage; it is not a claim that the entire login UI has been thoroughly tested.

## 4. Requirements and Planned Coverage

The source material does not provide formal requirement IDs. The following IDs are assigned locally for this plan.

| Local Requirement ID | Requirement | Planned Test | Planned observable result |
| --- | --- | --- | --- |
| REQ-SF-LOGIN-001 (local) | An active account with valid credentials can authenticate. | TC-SF-LOGIN-001 | The browser reaches an authenticated state agreed with the Salesforce application owner. A redirect away from the login host is a proposed indicator only and must be confirmed. |
| REQ-SF-LOGIN-002 (local) | An incorrect password does not authenticate the account. | TC-SF-LOGIN-002 | The account remains unauthenticated and Salesforce presents a login failure state. The exact message and DOM indicator are not yet confirmed. |

**TC-SF-LOGIN-001 — Valid credentials:** Open the login URL, enter credentials for an approved active account, submit the username-first and password steps as presented, then verify the agreed authenticated state.

**TC-SF-LOGIN-002 — Incorrect password:** Open the login URL, enter the approved account username and an approved invalid password, submit the login flow, then verify the account remains unauthenticated and an agreed login failure state is visible.

## 5. Test Approach, Levels, and Types

- **Test level:** End-to-end UI functional testing.
- **Test type:** One positive authentication scenario and one negative authentication scenario.
- **Automation:** Selenium WebDriver with Java, TestNG, Maven, Page Object Model, PageFactory, XPath locators, and condition-based waits, matching the approved source prompt.
- **Browser:** Chrome on macOS for the initial target. Chrome version and execution environment should be recorded at run time.
- **Isolation:** Start a fresh browser for each test and close it after the test, including on failure.
- **Assertions:** Use observable authenticated and unauthenticated states. Do not assert undocumented exact copy or assume that a URL change alone proves successful authentication until confirmed.
- **Execution status:** Not executed as part of this plan.

## 6. Environment, Tools, Access, and Test Data

| Item | Planned value or status |
| --- | --- |
| Application URL | `https://login.salesforce.com/?locale=in` as supplied; confirm that this is the approved test target. |
| Operating system | macOS for the initial local run. |
| Browser | Chrome; exact version not provided. |
| Language and runtime | Java 17, as configured in the generated Maven project. |
| Automation dependencies | Selenium Java 4.27.0 and TestNG 7.10.2, as configured in `Selenium framework/pom.xml`. |
| Build and execution | Maven with the TestNG suite in `Selenium framework/testng.xml`. |
| Account access | Not provided. An authorized active test account is required; no credential values belong in this document or source control. |
| Credential delivery | Supply approved values through the environment variables expected by the test project: `SALESFORCE_USERNAME`, `SALESFORCE_PASSWORD`, and `SALESFORCE_INVALID_PASSWORD`. Do not record their values in the plan, logs, screenshots, or test reports. |
| Account policy | Confirm the account can complete the selected flow without an out-of-scope MFA/SSO interaction, CAPTCHA, or other human-only challenge. Do not disable organizational security controls to make automation pass. |
| Locator and result evidence | The username-first page was observed, but password-step, failure-state, and authenticated-state locators remain unverified. Confirm them in the approved target before execution. |

## 7. Entry and Exit Criteria

### Entry criteria

- The application owner confirms the target URL and authorizes automated testing against it.
- An approved, active test account and invalid-password test value are provisioned through the approved secret mechanism.
- Account lockout, rate-limit, MFA/SSO, and CAPTCHA behavior is understood sufficiently to avoid unintended account or service impact.
- Current DOM locators for the password step, submit action, failure state, and authenticated state are verified against the approved target.
- Chrome, Java 17 or compatible runtime, and Maven are available in the execution environment.

### Exit criteria

- Both planned cases have recorded execution results, evidence, and environment details.
- Each case meets its confirmed observable expected result, or any deviation is recorded as a defect.
- No credentials or other secrets appear in committed files, logs, screenshots, or reports.
- Any remaining blockers, failed cases, and open defects are reported to the application owner.

The two-case count is fixed by the agreed scope. Any pass-rate or defect-severity threshold beyond these criteria is **not provided** and requires team agreement.

## 8. Roles, Responsibilities, Estimates, and Schedule

| Role | Responsibility | Assignment / schedule |
| --- | --- | --- |
| QA engineer | Confirm prerequisites, run the two tests, retain sanitized evidence, and report defects. | Owner and dates: Not provided. |
| Salesforce application owner | Approve the target and test account; confirm authentication outcomes, account policies, and locator changes. | Owner and dates: Not provided. |
| Development/support team | Triage reproducible failures and provide application behavior clarification. | Owner and dates: Not provided. |

Effort and schedule are not estimated because execution environment, account provisioning, and ownership dates were not supplied.

## 9. Defect Management and Reporting

Use the team's approved issue tracker; its name and workflow are not provided. Report reproducible failures with the local test ID, browser and version, operating system, target URL, timestamp, sanitized steps, expected result, actual result, and relevant sanitized screenshot or log excerpt. Never attach credentials, session cookies, access tokens, or other secrets. Classify severity and priority using the team's agreed conventions; do not infer them in this plan.

## 10. Risks, Dependencies, Assumptions, and Open Questions

### Risks and dependencies

- The requested URL is the public Salesforce production login host. Confirm authorization and use of an approved test account before running automation; a sandbox target may be more appropriate if the application owner directs it.
- Salesforce may require MFA, SSO, CAPTCHA, rate limiting, or account verification. These can prevent unattended execution and are outside the approved two-scenario scope.
- The live page was observed in a username-first state. The password and error XPath examples in the source prompt are unverified and may not match the current DOM.
- The successful-login URL may remain on a Salesforce login or identity domain; URL change alone may not establish authentication.
- Invalid-password attempts may trigger security monitoring or account protection. Use only an approved test account and follow the account owner's limits.
- No test account, credentials, exact failure message, confirmed success indicator, or execution environment details were supplied.

### Assumptions

- The approved active test account is authorized for this test and can complete login without a human-only challenge.
- The invalid-password value is approved for testing and does not create unacceptable lockout risk.
- The two planned cases remain the complete scope unless formally expanded.

### Open questions

- Is the production login host approved, or should the plan target a Salesforce sandbox URL?
- What authenticated-state condition should be used to confirm successful login?
- What failure-state element should be asserted for an incorrect password, and is exact message text required?
- Does the test account use MFA, SSO, CAPTCHA, or other controls that affect unattended execution?
- What are the approved account lockout and invalid-attempt limits?

## 11. Suspension and Resumption Criteria

Suspend execution if the target is not authorized, the test account is unavailable or unexpectedly locked, Salesforce presents an unplanned MFA/SSO/CAPTCHA challenge, repeated attempts trigger rate limits, the login page materially differs from verified locators, or the service appears degraded. Notify the application owner and record the observed condition without exposing secrets.

Resume only after the application owner confirms the target and account are safe to use, access/security prerequisites are resolved, and the relevant locators and expected outcomes are revalidated.

## 12. Test Deliverables and Approval

### Deliverables

- This test plan: `Test Plan/Salesforce_Login_Test_Plan.md`.
- Two scoped TestNG cases in the generated Selenium project: `Selenium framework/src/test/java/com/example/salesforce/tests/ValidLoginTest.java` and `Selenium framework/src/test/java/com/example/salesforce/tests/InvalidLoginTest.java`.
- Execution results and sanitized defect evidence, to be produced only after tests are run.

### Approval

| Approver | Name | Decision | Date |
| --- | --- | --- | --- |
| QA reviewer | Not provided | Pending | Not provided |
| Salesforce application owner | Not provided | Pending | Not provided |

**Validation status:** The plan is generated, not executed. Earlier editor diagnostics reported no Java source errors; Maven build and Salesforce test execution have not been verified.