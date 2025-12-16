Sample minimal Python package for CI demonstration

How to run locally

- Create a virtualenv and activate it:
  - python -m venv .venv && source .venv/bin/activate
- Install test dependencies:
  - pip install -r requirements-dev.txt
- Run tests:
  - pytest

Purpose
- This tiny package exists to provide a quick, runnable target for CI and to demonstrate how to add other small samples (Android/Flutter) that can be reliably tested in GitHub Actions.