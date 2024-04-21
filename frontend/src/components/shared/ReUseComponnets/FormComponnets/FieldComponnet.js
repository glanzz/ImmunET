import React from "react";

export default function FieldComponnet({ label, children, htmlFor, error }) {
  const id = htmlFor || getChildId(children);
  return (
    <div className="-mx-3 md:flex mb-2">
      <div className="md:w-full px-3">
        {/*  htmlFor will be same as id of the input element */}
        {label && (
          <label
            className="uppercase tracking-wide text-black text-xs font-bold mb-2"
            htmlFor={id}
          >
            {label}
          </label>
        )}
        {/*   this children is a input box that we will pass */}
        {children}
        {/*   error is a object */}
        {!!error && <div className="text-sm text-red-500">{error.message}</div>}
      </div>
    </div>
  );
}

/* this function will help to get children input element id */
const getChildId = (children) => {
  const child = React.Children.only(children);
  if ("id" in child?.props) {
    return child.props.id;
  }
};
