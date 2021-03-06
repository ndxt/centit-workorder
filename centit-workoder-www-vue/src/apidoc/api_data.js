define({ "api": [
  {
    "type": "get",
    "url": "queryDictionaryDetails(id)",
    "title": "Request User information",
    "name": "GetUser",
    "group": "cp",
    "examples": [
      {
        "title": "Example usage:",
        "content": "import {queryDictionaryDetails} from 'api/cp'\nconst data = await queryDictionaryDetails('style')",
        "type": "curl"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Users unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "firstname",
            "description": "<p>Firstname of the User.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "lastname",
            "description": "<p>Lastname of the User.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/cp.js",
    "groupTitle": "cp"
  }
] });
