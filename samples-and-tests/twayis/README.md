# Running YABE Sample App

## Prerequisies
* A test instance of redis is installed and running in a development environment
* The foreman Ruby gem is installed
* The heroku Ruby client is installed

## Running Locally
In the root of the application, create a `dev_profile` file.  Add the following environment variables:

    FRAMEWORK_ID=dev
    USE_PRECOMPILED=false
    
    REDISTOGO_URL=redis://local@localhost:6379

Now execute:

    $ foreman start --env=dev_profile

## Deploy to Heroku
From the application root, execute:

    $ heroku create [app_name] -s cedar
    $ heroku config:add FRAMEWORK_ID=prod
    $ heroku config:add USE_PRECOMPILED=true
    $ heroku addons:add redistogo
    $ git push heroku master
