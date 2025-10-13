from playwright.sync_api import sync_playwright

def run(playwright):
    browser = playwright.chromium.launch(headless=True)
    context = browser.new_context()
    page = context.new_page()

    # Because the backend is not running, I will navigate to the frontend directly.
    # This will not allow me to test the full functionality, but I can at least
    # verify that the pages render correctly.

    # Navigate to the Mental Wellness page
    page.goto("file:///app/demo1/project1/index.html#/mental-wellness")
    page.screenshot(path="jules-scratch/verification/mental-wellness.png")

    # Navigate to the Counsellor Registration page
    page.goto("file:///app/demo1/project1/index.html#/counsellor-registration")
    page.screenshot(path="jules-scratch/verification/counsellor-registration.png")

    # Navigate to the Find Counsellor page
    page.goto("file:///app/demo1/project1/index.html#/find-counsellor")
    page.screenshot(path="jules-scratch/verification/find-counsellor.png")

    # Navigate to the My Sessions page
    page.goto("file:///app/demo1/project1/index.html#/my-sessions")
    page.screenshot(path="jules-scratch/verification/my-sessions.png")

    browser.close()

with sync_playwright() as playwright:
    run(playwright)