#!/bin/sh
OLD_VERSION=$1
NEW_VERSION=$2
HOME=$(pwd)

# shellcheck disable=SC2164
cd "$HOME"/docs/

sed -i '' "s/${OLD_VERSION}/${NEW_VERSION}/g" mkdocs.yml

# shellcheck disable=SC2164
cd "$HOME"