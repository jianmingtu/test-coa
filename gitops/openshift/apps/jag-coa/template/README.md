## Templates to create openshift components related to jag-coa api deployment

### Command to execute template
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod
   ``oc process -f jag-coa.yaml --param-file=jag-coa.env | oc apply -f -``


