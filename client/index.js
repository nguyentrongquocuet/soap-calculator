const { default: Axios } = require('axios')
const { XMLParser } = require('fast-xml-parser')

const parser = new XMLParser()

const URL = 'http://localhost:8080/soapWS'

const parseXML = (xml) => parser.parse(xml)

const extractResponseBody = (xml) => {
	const parsedXml = parseXML(xml)

	return parsedXml['SOAP-ENV:Envelope']['SOAP-ENV:Body']
}

const extractOperationResult = (responseBody) => {
	return responseBody['ns2:calculatorResponse']['ns2:result']
}

const buildOperation = (ip1, op, ip2) => {
	return `
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:us="http://quoc.com/soap-calculator">
    <soapenv:Header/>
    <soapenv:Body>
        <us:calculatorRequest>
            <us:input1>${ip1}</us:input1>
            <us:operation>${op}</us:operation>
            <us:input2>${ip2}</us:input2>
        </us:calculatorRequest>
    </soapenv:Body>
</soapenv:Envelope>
	`
}

const add = (m, n) => buildOperation(m, '+', n)

const substract = (m, n) => buildOperation(m, '-', n)

const multiply = (m, n) => buildOperation(m, '*', n)

const power = (m, n) => buildOperation(m, '**', n)

const divide = (m, n) => buildOperation(m, '/', n)

const perform = (operationXml) => Axios.post(URL, operationXml, { headers: { 'Content-Type': 'text/xml' } })
	.then(res => res.data)
	.then(extractResponseBody)
	.then(extractOperationResult)


perform(add(1, 2)).then(console.log)

